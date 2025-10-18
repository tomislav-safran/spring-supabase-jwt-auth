package com.tsafran.springsupabasejwtauth.context;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class RlsContextSetter {
    private final DataSource dataSource;

    public void applyClaimsJson(String claimsJson) {
        if (claimsJson == null) return;

        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (Statement statement = connection.createStatement()) {
            statement.execute("set local role authenticated");

            try (PreparedStatement ps = connection.prepareStatement(
                    "select set_config('request.jwt.claims', ?, true)")) {
                ps.setString(1, claimsJson);
                ps.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set Postgres session", e);
        }
    }
}
