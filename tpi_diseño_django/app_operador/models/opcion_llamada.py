from django.db import models
from .llamada import Llamada
from .categoria_llamada import CategoriaLlamada


class OpcionLlamada(models.Model):
    audioMensajeSubOpciones = models.TextField(null=True)
    mensajeSubOpciones = models.TextField(null=True)
    nroOrden = models.IntegerField(null=True)
    nombre = models.CharField(max_length=255, default='')

    # Relaciones con otros modelos
    # subOpciones = models.ManyToManyField(SubOpcionLlamada)
    categoria = models.ForeignKey(CategoriaLlamada, on_delete=models.CASCADE)
    llamada = models.ManyToManyField(Llamada)

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
