from django.db import models
from .llamada import Llamada
from .opcion_llamada import OpcionLlamada


class SubOpcionLlamada(models.Model):
    nombre = models.CharField(max_length=255, default='')
    nroOrden = models.IntegerField(default=0)
    opcion = models.ForeignKey(OpcionLlamada, on_delete=models.CASCADE)
    llamada = models.ForeignKey(
        Llamada,
        on_delete=models.CASCADE,
        null=True
    )

    def __str__(self) -> str:
        return self.nombre

    def esNroOrden(self, numero):
        return self.getNroOrden() == numero

    def getNroOrden(self):
        return int(self.nroOrden)

    def setNroOrden(self, nuevoNroOrden):
        self.nroOrden = nuevoNroOrden
        self.save()

    def getNombre(self):
        return self.nombre

    def setNombre(self, nuevoNombre):
        self.nombre = nuevoNombre
        self.save()

    class Meta:
        db_table = 'sub_opcion_llamada'
