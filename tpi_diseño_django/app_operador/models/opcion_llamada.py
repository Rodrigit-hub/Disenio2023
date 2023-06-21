from django.db import models
from .sub_opcion_llamada import SubOpcionLlamada
from .validacion import Validacion


class OpcionLlamada(models.Model):
    audioMensajeSubopciones = models.CharField(max_length=255, null=True)
    mensajeSubopciones = models.TextField(null=True)
    nroOrden = models.IntegerField(null=True)
    nombre = models.CharField(max_length=255, default='')

    # Relaciones con otros modelos
    subOpciones = models.ManyToManyField(SubOpcionLlamada)
    validaciones = models.ManyToManyField(Validacion)

    def __str__(self) -> str:
        return self.getNombre()

    def getNombre(self):
        return str(self.nombre)

    def getDescripcionConSubOpcion(self):
        pass
        # descripcion = f"{self.nombre}: {self.mensajeSubopciones}"
        # sub_opciones = ", ".join([str(sub_opcion) for sub_opcion in self.sub_opciones.all()])
        # return f"{descripcion} ({sub_opciones})"

    def buscarValidaciones(self):
        pass
        # return self.validaciones.all()

    class Meta:
        db_table = 'opcion_llamada'
