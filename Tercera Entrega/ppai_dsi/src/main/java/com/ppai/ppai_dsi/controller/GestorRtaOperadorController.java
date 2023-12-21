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
import com.ppai.ppai_dsi.domain.iterators.IIterador;
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
        // Obtener el estado existente "Iniciada" desde la base de datos
        Optional<Estado> optionalEstadoIniciada = servicesEstado.obtenerEstadoPorNombre("Iniciada");
        
        //Calculo de fecha actual
        Date fechaCreacion = obtenerFechaYHoraActual();

        if (optionalCliente.isPresent() && optionalCategoriaLlamada.isPresent() && optionalEstadoIniciada.isPresent()) {
            llamada.setCliente(optionalCliente.get());
            llamada.setCategoriaLlamada(optionalCategoriaLlamada.get());
            llamada.setEstadoActual(optionalEstadoIniciada.get());
        }

        //Obtener estado actual de llamada
        Estado estadoActual = llamada.getEstadoActual();

        llamada.tomadaPorOperador(fechaCreacion, estadoActual, servicesCambioEstado, servicesEstado);

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

    //SE APLICA PATRON ITERATOR
    public void obtenerDatosLlamada(Model model, Llamada llamada) {
        // Declarar variables fuera del bucle
        String clienteLlamada = null;
        String nombreCategoria = null;
        String nombreOpcion = null;
        String nombreSubOpcion = null;

        // Obtener un iterador para Llamada
        IIterador iteradorLlamada = crearIterador();

        iteradorLlamada.primero();
        
        while (!iteradorLlamada.haTerminado()) {
            // Obtener el nombre de cada elemento de Llamada
            clienteLlamada = (String) iteradorLlamada.actual();
            // Realizar operaciones con el nombre
            System.out.println("Nombre de Llamada: " + clienteLlamada);

            // Obtener un iterador para CategoriaLlamada (asumiendo que existe una relación)
            IIterador iteradorCategoria = llamada.getCategoriaLlamada().crearIterador();

            while (!iteradorCategoria.haTerminado()) {
                // Obtener el nombre de cada elemento de CategoriaLlamada
                nombreCategoria = (String) iteradorCategoria.actual();
                // Realizar operaciones con el nombre
                System.out.println("Nombre de CategoriaLlamada: " + nombreCategoria);

                // Obtener un iterador para OpcionLlamada
                IIterador iteradorOpcion = llamada.getCategoriaLlamada().getOpcionLlamada().crearIterador();

                while (!iteradorOpcion.haTerminado()) {
                    // Obtener el nombre de cada elemento de OpcionLlamada
                    nombreOpcion = (String) iteradorOpcion.actual();
                    // Realizar operaciones con el nombre
                    System.out.println("Nombre de OpcionLlamada: " + nombreOpcion);

                    // Obtener un iterador para SubOpcionLlamada
                    IIterador iteradorSubOpcion = llamada.getCategoriaLlamada().getOpcionLlamada().getSubOpcionLlamada().crearIterador();                   
                  
                    while (!iteradorSubOpcion.haTerminado()) {
                        // Obtener el nombre de cada elemento de SubOpcionLlamada
                        nombreSubOpcion = (String) iteradorSubOpcion.actual();
                        // Realizar operaciones con el nombre
                        System.out.println("Nombre de SubOpcionLlamada: " + nombreSubOpcion);

                        // Mover al siguiente elemento de SubOpcionLlamada
                        iteradorSubOpcion.siguiente();
                    }

                    // Mover al siguiente elemento de OpcionLlamada
                    iteradorOpcion.siguiente();
                }

                // Mover al siguiente elemento de CategoriaLlamada
                iteradorCategoria.siguiente();
            }

            // Mover al siguiente elemento de Llamada
            iteradorLlamada.siguiente();
        }

        // Puedes agregar los nombres al modelo si necesitas usarlos en la vista
        model.addAttribute("cliente", clienteLlamada);
        model.addAttribute("categoria", nombreCategoria);
        model.addAttribute("opcionLlamada", nombreOpcion);
        model.addAttribute("subOpcion", nombreSubOpcion);
    }
    
    public IIterador crearIterador() {
        return llamada.crearIterador();
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

        //Obtener el estado actual de la llamada
        Estado estadoCurso = llamada.getEstadoActual();
        //Finalizar llamada
        llamada.finalizarLlamada(fechaHoraActual, estadoCurso, servicesCambioEstado, servicesEstado);

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
            
        //Obtener el estado actual de la llamada
        Estado estadoCurso = llamada.getEstadoActual();
        //Cancelar Llamada
        llamada.cancelarLlamada(fechaHoraActual, estadoCurso, servicesCambioEstado, servicesEstado);

        llamada.calcularDuracion(cambioEstadoAnterior);

        servicesLlamada.save(llamada);

        return "cancelar";
    }
    

    public Date obtenerFechaYHoraActual() {
        return new Date();
    }
}
