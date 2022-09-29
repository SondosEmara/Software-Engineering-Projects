# Generated by Django 3.2.5 on 2021-07-10 00:57

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Book',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Tittle', models.CharField(max_length=50)),
                ('ISBN', models.CharField(max_length=50)),
                ('PublicationYear', models.DateField()),
                ('Author', models.CharField(max_length=30)),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('UserName', models.CharField(max_length=30)),
                ('Email', models.CharField(max_length=100)),
                ('Password', models.CharField(max_length=30)),
                ('Phone', models.CharField(max_length=20)),
            ],
        ),
    ]
