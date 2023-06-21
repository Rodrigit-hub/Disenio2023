from django.db import models
from .informacion_cliente import InformacionCliente


class Cliente(models.Model):
    dni = models.CharField(max_length=9, unique=True, default='')
    nombre = models.CharField(max_length=255, default='')
    nroCelular = models.CharField(max_length=20, null=True)

    # Puntero
    info = models.ManyToManyField(InformacionCliente)

    def __str__(self):
        return self.getNombre()

    # cliente: 'Cliente' es una anotación para decir que ese parámetro es del tipo Cliente
    def esCliente(self, cliente: 'Cliente'):
        return self.getDni() == cliente.getDni()

    def getDni(self):
        return str(self.dni)

    def setDni(self, nuevoDni: str):
        self.dni = nuevoDni
        # Almacena el cambio en la base de datos
        self.save()

    def getNombre(self):
        return str(self.nombre)

    def setNombre(self, nuevoNombre):
        self.nombre = nuevoNombre
        # Almacena el cambio en la base de datos
        self.save()

    class Meta:
        db_table = 'cliente'
