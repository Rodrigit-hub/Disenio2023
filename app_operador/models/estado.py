from django.db import models


class Estado(models.Model):
    nombre = models.CharField(max_length=100, default='iniciada')

    # No hace falta hacer esto, pero se puede hacer
    # def __init__(self):
    #     self.nombre = None

    def __str__(self):
        return self.getNombre()

    def getNombre(self) -> str:
        return str(self.nombre)

    def setNombre(self, nuevoNombre: str):
        self.nombre = nuevoNombre
        self.save()

    def esFinalizada(self) -> bool:
        return self.getNombre().lower() == 'finalizada'

    def esIniciada(self) -> bool:
        return self.getNombre().lower() == 'iniciada'

    class Meta:
        db_table = 'estado'
