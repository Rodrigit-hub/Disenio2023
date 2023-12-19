package com.ppai.ppai_dsi.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.ppai.ppai_dsi.domain.Cliente;

public interface IClienteServices {
    public List<Cliente> listarClientes();
    public Optional<Cliente> listarIdClientes(int id);
    public int save (Cliente cliente);
    public void delete(int id);
}
