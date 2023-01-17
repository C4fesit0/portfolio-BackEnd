package com.backend.util.date;

import java.sql.Date;

public class Fecha {

    public static Date convertir(String fecha){
        Date fechaConvertida = Date.valueOf(fecha);
        return fechaConvertida;
    }
}
