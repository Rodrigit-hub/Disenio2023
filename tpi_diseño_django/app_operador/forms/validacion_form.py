from django import forms


class VerificarValidacionForm(forms.Form):
    id_llamada = forms.CharField(max_length=100)
    nombre_cliente = forms.CharField(max_length=100)
    estado_llamada = forms.CharField(max_length=100)
    categoria_seleccionada = forms.CharField(max_length=100)
    opcion_seleccionada = forms.CharField(max_length=100)
    sub_opcion_seleccionada = forms.CharField(max_length=100)
    validacion = forms.CharField(max_length=100)
