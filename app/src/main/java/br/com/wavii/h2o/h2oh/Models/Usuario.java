package br.com.wavii.h2o.h2oh.Models;

/**
 * Created by lucas.ricarte on 17/08/2017.
 */

public class Usuario {

    private int codigo;
    private String email;
    private String senha;

    public Usuario( int codigo, String email, String senha){
        this.setCodigo(codigo);
        this.setEmail(email);
        this.setSenha(senha);
    }

    public Usuario(){

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
