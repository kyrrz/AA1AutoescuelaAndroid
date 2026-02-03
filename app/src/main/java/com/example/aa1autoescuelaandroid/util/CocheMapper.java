package com.example.aa1autoescuelaandroid.util;

import com.example.aa1autoescuelaandroid.db.CocheEntity;
import com.example.aa1autoescuelaandroid.domain.Coche;

public class CocheMapper {

    public static CocheEntity toEntity(Coche coche) {
        if (coche == null) return null;
        CocheEntity entity = new CocheEntity();
        entity.setId(coche.getId());
        entity.setMatricula(coche.getMatricula());
        entity.setMarca(coche.getMarca());
        entity.setModelo(coche.getModelo());
        entity.setTipoCambio(coche.getTipoCambio());
        entity.setKilometraje(coche.getKilometraje());
        entity.setFechaCompra(coche.getFechaCompra());
        entity.setPrecioCompra(coche.getPrecioCompra());
        entity.setDisponible(coche.isDisponible());
        entity.setAutoescuelaId(coche.getAutoescuelaId());
        entity.setImage(coche.getImage());
        return entity;
    }

    public static Coche toDomain(CocheEntity entity) {
        if (entity == null) return null;
        return Coche.builder()
                .id(entity.getId())
                .matricula(entity.getMatricula())
                .marca(entity.getMarca())
                .modelo(entity.getModelo())
                .tipoCambio(entity.getTipoCambio())
                .kilometraje(entity.getKilometraje())
                .fechaCompra(entity.getFechaCompra())
                .precioCompra(entity.getPrecioCompra())
                .disponible(entity.isDisponible())
                .autoescuelaId(entity.getAutoescuelaId())
                .image(entity.getImage())
                .build();
    }
}