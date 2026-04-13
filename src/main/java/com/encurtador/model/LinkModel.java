package com.encurtador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LinkModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String url;

    public Long getId(){return id; }

    public String getCodigo() {return codigo; }
    public void setCodigo(String codigo) {this.codigo = codigo; }

    public String getUrl() {return url; }
    public void setUrl(String url) {this.url = url; }
}
