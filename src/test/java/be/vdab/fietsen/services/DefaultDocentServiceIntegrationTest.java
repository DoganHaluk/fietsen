package be.vdab.fietsen.services;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultDocentServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String DOCENTEN = "docenten";
    private final DefaultDocentService service;
    private final EntityManager manager;

    public DefaultDocentServiceIntegrationTest(DefaultDocentService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }

    private long idVanTestMan() {
        return jdbcTemplate.queryForObject("select id from docenten where voornaam = 'testM'", Long.class);
    }

    @Test
    void opslag() {
        var id = idVanTestMan();
        service.opslag(id, BigDecimal.TEN);
        manager.flush();
        assertThat(countRowsInTableWhere(DOCENTEN, "wedde = 1100 and id = " + id)).isOne();
    }
}
