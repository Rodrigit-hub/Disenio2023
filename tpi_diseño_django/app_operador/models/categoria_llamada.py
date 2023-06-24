from django.db import models
from .llamada import Llamada


class CategoriaLlamada(models.Model):
    audioMensajeOpciones = models.CharField(max_length=255, default='')
    mensajeOpciones = models.TextField(null=True)
    nroOrden = models.IntegerField(default=0)
    nombre = models.CharField(max_length=255, default='')
    # Relaciones con otros modelos
    # opciones = models.ManyToManyField(OpcionLlamada)

    # La llamada necesita conocer Categoría, Opción y SubOpción marcadas por el cliente
    llamada = models.ManyToManyField(Llamada)

    def __str__(self):
        return self.getNombre()

    def getNombre(self):
        return str(self.nombre)

    def buscarValidaciones(self):
        pass

    def setNombre(self, nuevoNombre):
        self.nombre = nuevoNombre
        self.save()

    def getNroOrden(self):
        return int(self.nroOrden)

    def setNroOrden(self, nuevoNroOrden):
        self.nroOrden = nuevoNroOrden

    def getMensajeSubopciones(self):
        return str(self.mensajeSubopciones)

    def setMensajeSubopciones(self, nuevoMensajeSubopciones):
        self.mensajeSubopciones = nuevoMensajeSubopciones
        self.save()

    def getAudioMensajeSubopciones(self):
        return str(self.audioMensajeSubopciones)

    def setAudioMensajeSubopciones(self, nuevoAudioMensajeSubopciones):
        self.audioMensajeSubopciones = nuevoAudioMensajeSubopciones
        self.save()

    def getDescripcionConSubOpcion(self):
        pass
        # descripcion = f"{self.nombre}: {self.mensajeSubopciones}"
        # sub_opciones = ", ".join([str(sub_opcion) for sub_opcion in self.sub_opciones.all()])
        # return f"{descripcion} ({sub_opciones})"

    def getCategoriaOpcionSubOpcion(self):
        pass

    def buscarValidaciones(self):
        pass
        # return self.validaciones.all()

    class Meta:
        db_table = 'categoria_llamada'
