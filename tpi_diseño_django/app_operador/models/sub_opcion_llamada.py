from django.db import models


class SubOpcionLlamada(models.Model):
    nombre = models.CharField(max_length=255, default='')
    nroOrden = models.IntegerField(default=0)

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
