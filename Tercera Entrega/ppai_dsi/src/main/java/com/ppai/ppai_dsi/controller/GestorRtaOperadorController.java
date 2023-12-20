package com.ppai.ppai_dsi.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ppai.ppai_dsi.domain.CambioEstado;
import com.ppai.ppai_dsi.domain.CategoriaLlamada;
import com.ppai.ppai_dsi.domain.Cliente;
import com.ppai.ppai_dsi.domain.Estado;
import com.ppai.ppai_dsi.domain.Llamada;
import com.ppai.ppai_dsi.domain.Validacion;
import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.ICategoriaLlamadaServices;
import com.ppai.ppai_dsi.interfaceServices.IClienteServices;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.ILlamadaServices;
import com.ppai.ppai_dsi.interfaceServices.IValidacionServices;




@Controller
@RequestMapping
public class GestorRtaOperadorController {
    @Autowired
    private ILlamadaServices servicesLlamada;
    @Autowired
    private IClienteServices servicesCliente;
    @Autowired
    private ICambioEstadoServices servicesCambioEstado;
    @Autowired
    private ICategoriaLlamadaServices servicesCategoriaLlamada;
    @Autowired
    private IEstadoServices servicesEstado;
    @Autowired
    private IValidacionServices servicesValidaciones;

    //Crear llamada
    Llamada llamada = new Llamada();
    
    // PASO 2
    @RequestMapping(value = "/recibir_llamada", method = {RequestMethod.GET, RequestMethod.POST})
    public String recibirLlamada(Model model){

        //Obtener cliente desde BD
        Optional<Cliente> optionalCliente = servicesCliente.listarIdClientes(1);
        //Obtener categoria desde BD
        Optional<CategoriaLlamada> optionalCategoriaLlamada = servicesCategoriaLlamada.listarIdCategoriaLlamadas(1);
        // Obtener el estado existente "EnCurso" desde la base de datos
        Optional<Estado> optionalEstadoEnCurso = servicesEstado.obtenerEstadoPorNombre("EnCurso");
        
        //Calculo de fecha actual
        Date fechaCreacion = obtenerFechaYHoraActual();

        if (optionalCliente.isPresent() && optionalCategoriaLlamada.isPresent() && optionalEstadoEnCurso.isPresent()) {
            llamada.setCliente(optionalCliente.get());
            llamada.setCategoriaLlamada(optionalCategoriaLlamada.get());
            llamada.setEstadoActual(optionalEstadoEnCurso.get());
        }

        //Obtener estado actual de llamada
        Estado estadoActual = llamada.getEstadoActual();

        //PASO 3
        Boolean esEstadoEnCurso = buscarEstadoEnCurso(estadoActual);

        //PASO 5
        if(esEstadoEnCurso){
            llamada.tomadaPorOperador(fechaCreacion, estadoActual, servicesCambioEstado);
        } else {
            System.out.println("La llamada no es estado en curso");
        }

        //PASO 7
        obtenerDatosLlamada(model, llamada);

        //PASO 13
        buscarValidaciones(model);

        servicesLlamada.save(llamada);
        return "recibir_llamada";

    }

    //PASO 4
    public boolean buscarEstadoEnCurso(Estado estado) {
        return estado.esEnCurso();
    }

    //PASO 8
    public void obtenerDatosLlamada(Model model, Llamada llamada) {

        String cliente = llamada.getCliente().getNombreCompleto();

        String categoria = llamada.getCategoriaLlamada().getNombre();
        String opcionLlamada = llamada.getCategoriaLlamada().getOpcionLlamada().getNombre();
        String subopcion = llamada.getCategoriaLlamada().getOpcionLlamada().getSubOpcionLlamada().getNombre();

        model.addAttribute("cliente", cliente);
        model.addAttribute("categoria", categoria);
        model.addAttribute("opcionLlamada", opcionLlamada);
        model.addAttribute("subopcion", subopcion);
    }

    //PASO 13
    public void buscarValidaciones(Model model){
        List<Validacion> lista_validaciones = servicesValidaciones.listarValidaciones();
        model.addAttribute("lista_validaciones", lista_validaciones);
    }
   
//---------------------------------------------------------------------------------------
    //PASO 21
    @PostMapping("/validar_llamada")
    public String validarInformacionCliente(@RequestParam Map<String, String> params, Model model) {
        Cliente cliente = llamada.getCliente();
        boolean seValida = false;
        
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String nombreCampo = entry.getKey();
            String valorIngresado = entry.getValue();

            //PASO 22
            seValida = cliente.esInformacionCorrecta(nombreCampo, valorIngresado);

            // Si no se valida en alguna validación, puedes salir del bucle
            if (!seValida) {
                break;
            }
        }
        model.addAttribute("cliente", cliente);
        if (seValida) {
            return "validado";
        } else {
            return "no_validado";
        }

 
    }
//---------------------------------------------------------------------------------------
    //PASO 33
    @PostMapping("/finalizar_llamada")
    public String finalizarLlamada(@RequestParam(name = "descripcion") String descripcion,
        @RequestParam(name = "accion") String accion,
        @RequestParam(name = "encuesta") Boolean encuesta) {
        
        llamada.setDescripcionOperador(descripcion);
        llamada.setDetalleAccionRequerida(accion);
        llamada.setEncuestaEnviada(encuesta);

        CambioEstado cambioEstadoAnterior = llamada.getCambioEstado();
        
        //PASO 34
        Date fechaHoraActual = obtenerFechaYHoraActual();


        // Obtener el estado existente "En Curso" desde la base de datos
        Optional<Estado> optionalEstadoEnCurso = servicesEstado.obtenerEstadoPorNombre("EnCurso");
        if (optionalEstadoEnCurso.isPresent()){
            //PASO 36
            Estado estadoCurso = optionalEstadoEnCurso.get();
            //PASO 37
            llamada.finalizarLlamada(fechaHoraActual, estadoCurso, servicesCambioEstado, servicesEstado);
        }

        //PASO 39
        llamada.calcularDuracion(cambioEstadoAnterior);

        servicesLlamada.save(llamada);

        return "finalizar";
    }

//---------------------------------------------------------------------------------------
    @PostMapping("/cancelar_llamada")
    public String cancelarLlamada() {

        CambioEstado cambioEstadoAnterior = llamada.getCambioEstado();

        Date fechaHoraActual = obtenerFechaYHoraActual();

        //Setear los campos en vacio
        llamada.setDescripcionOperador("");
        llamada.setDetalleAccionRequerida("");
        llamada.setEncuestaEnviada(false);
            
        // Obtener el estado existente "En Curso" desde la base de datos
        Optional<Estado> optionalEstadoEnCurso = servicesEstado.obtenerEstadoPorNombre("EnCurso");
        if (optionalEstadoEnCurso.isPresent()){
            Estado estadoCurso = optionalEstadoEnCurso.get();
            llamada.cancelarLlamada(fechaHoraActual, estadoCurso, servicesCambioEstado, servicesEstado);
        }


        llamada.calcularDuracion(cambioEstadoAnterior);

        servicesLlamada.save(llamada);

        return "cancelar";
    }
    

    public Date obtenerFechaYHoraActual() {
        return new Date();
    }
}
