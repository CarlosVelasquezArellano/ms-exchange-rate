package com.nttdata.proxy;

import java.time.LocalDate;

public class TipoCambioProxy {

    private LocalDate fecha;
    private double sunat;
    private double compra;
    private double venta;

    public TipoCambioProxy() { }

    public TipoCambioProxy(LocalDate fecha, double sunat, double compra, double venta) {
        this.fecha = fecha;
        this.sunat = sunat;
        this.compra = compra;
        this.venta = venta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
}
