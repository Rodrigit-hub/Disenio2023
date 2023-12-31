# Generated by Django 4.2.2 on 2023-06-25 21:58

from django.db import migrations, models
import django.db.models.deletion
import django.utils.timezone


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='CategoriaLlamada',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('audioMensajeOpciones', models.CharField(default='', max_length=255)),
                ('mensajeOpciones', models.TextField(null=True)),
                ('nroOrden', models.IntegerField(default=0)),
                ('nombre', models.CharField(default='', max_length=255)),
            ],
            options={
                'db_table': 'categoria_llamada',
            },
        ),
        migrations.CreateModel(
            name='Cliente',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('dni', models.CharField(default='', max_length=9, unique=True)),
                ('nombre', models.CharField(default='', max_length=255)),
                ('nroCelular', models.CharField(max_length=20, null=True)),
            ],
            options={
                'db_table': 'cliente',
            },
        ),
        migrations.CreateModel(
            name='Estado',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(default='iniciada', max_length=100)),
            ],
            options={
                'db_table': 'estado',
            },
        ),
        migrations.CreateModel(
            name='Llamada',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('descripcionOperador', models.CharField(max_length=255, null=True)),
                ('detalleAccionRequerida', models.TextField(null=True)),
                ('duracion', models.CharField(max_length=50)),
                ('encuestaEnviada', models.BooleanField(null=True)),
                ('cliente', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.cliente')),
                ('estadoActual', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.estado')),
            ],
            options={
                'db_table': 'llamada',
            },
        ),
        migrations.CreateModel(
            name='OpcionLlamada',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('audioMensajeSubOpciones', models.TextField(null=True)),
                ('mensajeSubOpciones', models.TextField(null=True)),
                ('nroOrden', models.IntegerField(null=True)),
                ('nombre', models.CharField(default='', max_length=255)),
                ('categoria', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.categoriallamada')),
                ('llamada', models.ManyToManyField(to='app_operador.llamada')),
            ],
            options={
                'db_table': 'opcion_llamada',
            },
        ),
        migrations.CreateModel(
            name='SubOpcionLlamada',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(default='', max_length=255)),
                ('nroOrden', models.IntegerField(default=0)),
                ('llamada', models.ManyToManyField(to='app_operador.llamada')),
                ('opcion', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.opcionllamada')),
            ],
            options={
                'db_table': 'sub_opcion_llamada',
            },
        ),
        migrations.CreateModel(
            name='Validacion',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('audioMensajeValidacion', models.CharField(max_length=255, null=True)),
                ('nombre', models.CharField(max_length=100, null=True)),
                ('subOpcion', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='app_operador.subopcionllamada')),
            ],
            options={
                'db_table': 'validacion',
            },
        ),
        migrations.CreateModel(
            name='InformacionCliente',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('datoAValidar', models.CharField(max_length=255, null=True)),
                ('cliente', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.cliente')),
                ('validacion', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.validacion')),
            ],
            options={
                'db_table': 'informacion_cliente',
            },
        ),
        migrations.AddField(
            model_name='categoriallamada',
            name='llamada',
            field=models.ManyToManyField(to='app_operador.llamada'),
        ),
        migrations.CreateModel(
            name='CambioEstado',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('fechaHoraCambio', models.DateTimeField(default=django.utils.timezone.now)),
                ('estado', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.estado')),
                ('llamada', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_operador.llamada')),
            ],
            options={
                'db_table': 'cambio_estado',
            },
        ),
    ]
