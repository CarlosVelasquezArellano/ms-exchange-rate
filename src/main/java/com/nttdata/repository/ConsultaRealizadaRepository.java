package com.nttdata.repository;

import com.nttdata.entity.ConsultaRealizada;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ConsultaRealizadaRepository implements PanacheRepository<ConsultaRealizada> {

    public long cantidadRegistros(String documento) {
        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioDelDia = hoy.atStartOfDay();
        LocalDateTime inicioDelSiguienteDia = hoy.plusDays(1).atStartOfDay();

        return count(
                "documento = ?1 and feCreaRegistro >= ?2 and feCreaRegistro < ?3",
                documento,
                inicioDelDia,
                inicioDelSiguienteDia
        );
    }

    @Transactional
    public void registrar(ConsultaRealizada consulta) {
        persist(consulta);
    }


    public List<ConsultaRealizada> listarRegistros(String documento) {
        return list("documento = ?1", documento);
    }
}
