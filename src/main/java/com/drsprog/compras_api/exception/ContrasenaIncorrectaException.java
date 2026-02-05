package com.drsprog.compras_api.exception;

public class ContrasenaIncorrectaException extends RuntimeException {
    public ContrasenaIncorrectaException(){
        super("La contraseña actual es incorrecta");
    }
}
