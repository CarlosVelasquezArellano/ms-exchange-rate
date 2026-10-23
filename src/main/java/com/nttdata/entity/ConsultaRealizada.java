package com.nttdata.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ConsultaRealizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;
    private String documento;
    private double sunat;
    private double compra;
    private double venta;
    private LocalDateTime feCreaRegistro;

    public ConsultaRealizada() { }

    public ConsultaRealizada(Long idConsulta, String documento, double sunat, double compra, double venta, LocalDateTime feCreaRegistro) {
        this.idConsulta = idConsulta;
        this.documento = documento;
        this.sunat = sunat;
        this.compra = compra;
        this.venta = venta;
        this.feCreaRegistro = feCreaRegistro;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public double getSunat() {
        return sunat;
    }

    public void setSunat(double sunat) {
        this.sunat = sunat;
    }

    public double getCompra() {
        return compra;
    }

    public void setCompra(double compra) {
        this.compra = compra;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public LocalDateTime getFeCreaRegistro() {
        return feCreaRegistro;
    }

    public void setFeCreaRegistro(LocalDateTime feCreaRegistro) {
        this.feCreaRegistro = feCreaRegistro;
    }
}
