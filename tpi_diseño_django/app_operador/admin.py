from django.contrib import admin
from .models import CategoriaLlamada, Cliente, CambioEstado, Estado, InformacionCliente, Llamada, OpcionLlamada, OpcionValidacion, SubOpcionLlamada, TipoInformacion, Validacion

# Register your models here.
admin.site.register(Cliente)
admin.site.register(InformacionCliente)
admin.site.register(Estado)
admin.site.register(Validacion)
admin.site.register(CategoriaLlamada)
admin.site.register(OpcionLlamada)
admin.site.register(SubOpcionLlamada)
admin.site.register(CambioEstado)
admin.site.register(Llamada)
admin.site.register(OpcionValidacion)
admin.site.register(TipoInformacion)
