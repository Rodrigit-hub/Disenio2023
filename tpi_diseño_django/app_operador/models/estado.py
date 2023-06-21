from django.db import models


class Estado(models.Model):
    nombre = models.CharField(max_length=100, default='iniciada')

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

    def esEnCurso(self) -> bool:
        return self.getNombre().lower() == 'en curso'

    class Meta:
        db_table = 'estado'
