PGDMP     
                
    z         	   Vigilance    14.5    14.5 #    !           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            "           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            #           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            $           1262    16394 	   Vigilance    DATABASE     o   CREATE DATABASE "Vigilance" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE "Vigilance";
                postgres    false            �            1259    16558    alquiler    TABLE     D  CREATE TABLE public.alquiler (
    codigo_alq integer NOT NULL,
    id_serv integer,
    fechain date,
    fechafin date,
    descr character varying(255),
    cant integer,
    hruso integer,
    costou numeric(10,2),
    costomant numeric(10,2),
    status integer,
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.alquiler;
       public         heap    postgres    false            �            1259    16543    cliente    TABLE     l  CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nombre character varying(255),
    tiporep integer,
    nombrerep character varying(40),
    localidad character varying(55),
    direccion character varying(55),
    codigopostal integer,
    telf character varying(12),
    status integer,
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    16588    detalle_servicio    TABLE     �   CREATE TABLE public.detalle_servicio (
    id_vg integer NOT NULL,
    serv_cod integer NOT NULL,
    resumen character varying[]
);
 $   DROP TABLE public.detalle_servicio;
       public         heap    postgres    false            �            1259    16568    factura    TABLE     k  CREATE TABLE public.factura (
    nrofact integer NOT NULL,
    id_client integer,
    cod_serv integer,
    cod_alq integer,
    fecha_pago date,
    tipo_pago integer,
    descr character varying(255),
    monto_ser numeric(10,2),
    monto_alq numeric(10,2),
    total numeric(10,2),
    status integer,
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.factura;
       public         heap    postgres    false            �            1259    16513    horario    TABLE       CREATE TABLE public.horario (
    id_vg integer NOT NULL,
    fechain date,
    fechafin date,
    horain time without time zone,
    horafin time without time zone,
    faltas integer,
    just integer,
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.horario;
       public         heap    postgres    false            �            1259    16521    nomina    TABLE     S  CREATE TABLE public.nomina (
    nronomina integer NOT NULL,
    id_vg integer,
    fecha date,
    descr character varying(255),
    diasextra integer,
    hrextra integer,
    diasfaltas integer,
    sueldob numeric(10,2),
    pagoextra numeric(10,2),
    deduccion numeric(10,2),
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.nomina;
       public         heap    postgres    false            �            1259    16536    servicio    TABLE       CREATE TABLE public.servicio (
    codigo integer NOT NULL,
    client_id integer NOT NULL,
    fecha date,
    descr character varying[],
    turno timestamp without time zone[],
    vig_hab integer,
    status integer,
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.servicio;
       public         heap    postgres    false            �            1259    16508 	   vigilante    TABLE     ]  CREATE TABLE public.vigilante (
    id_vig integer NOT NULL,
    cedula character varying(12),
    apellido character varying(30),
    nombre character varying(30),
    edad integer,
    correo character varying(40),
    telf character varying(12),
    fechaing date,
    status numeric(1,0),
    created date,
    updated date,
    deleted date
);
    DROP TABLE public.vigilante;
       public         heap    postgres    false                      0    16558    alquiler 
   TABLE DATA           �   COPY public.alquiler (codigo_alq, id_serv, fechain, fechafin, descr, cant, hruso, costou, costomant, status, created, updated, deleted) FROM stdin;
    public          postgres    false    214   3/                 0    16543    cliente 
   TABLE DATA           �   COPY public.cliente (id_cliente, nombre, tiporep, nombrerep, localidad, direccion, codigopostal, telf, status, created, updated, deleted) FROM stdin;
    public          postgres    false    213   P/                 0    16588    detalle_servicio 
   TABLE DATA           D   COPY public.detalle_servicio (id_vg, serv_cod, resumen) FROM stdin;
    public          postgres    false    216   m/                 0    16568    factura 
   TABLE DATA           �   COPY public.factura (nrofact, id_client, cod_serv, cod_alq, fecha_pago, tipo_pago, descr, monto_ser, monto_alq, total, status, created, updated, deleted) FROM stdin;
    public          postgres    false    215   �/                 0    16513    horario 
   TABLE DATA           u   COPY public.horario (id_vg, fechain, fechafin, horain, horafin, faltas, just, created, updated, deleted) FROM stdin;
    public          postgres    false    210   �/                 0    16521    nomina 
   TABLE DATA           �   COPY public.nomina (nronomina, id_vg, fecha, descr, diasextra, hrextra, diasfaltas, sueldob, pagoextra, deduccion, created, updated, deleted) FROM stdin;
    public          postgres    false    211   �/                 0    16536    servicio 
   TABLE DATA           v   COPY public.servicio (codigo, client_id, fecha, descr, turno, vig_hab, status, created, updated, deleted) FROM stdin;
    public          postgres    false    212   �/                 0    16508 	   vigilante 
   TABLE DATA           �   COPY public.vigilante (id_vig, cedula, apellido, nombre, edad, correo, telf, fechaing, status, created, updated, deleted) FROM stdin;
    public          postgres    false    209   �/       ~           2606    16562    alquiler alquiler_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.alquiler
    ADD CONSTRAINT alquiler_pkey PRIMARY KEY (codigo_alq);
 @   ALTER TABLE ONLY public.alquiler DROP CONSTRAINT alquiler_pkey;
       public            postgres    false    214            |           2606    16547    cliente cliente_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    213            �           2606    16604 &   detalle_servicio detalle_servicio_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.detalle_servicio
    ADD CONSTRAINT detalle_servicio_pkey PRIMARY KEY (id_vg, serv_cod);
 P   ALTER TABLE ONLY public.detalle_servicio DROP CONSTRAINT detalle_servicio_pkey;
       public            postgres    false    216    216            �           2606    16572    factura factura_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (nrofact);
 >   ALTER TABLE ONLY public.factura DROP CONSTRAINT factura_pkey;
       public            postgres    false    215            z           2606    16542    servicio servicio_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (codigo);
 @   ALTER TABLE ONLY public.servicio DROP CONSTRAINT servicio_pkey;
       public            postgres    false    212            x           2606    16512    vigilante vigilante_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vigilante
    ADD CONSTRAINT vigilante_pkey PRIMARY KEY (id_vig);
 B   ALTER TABLE ONLY public.vigilante DROP CONSTRAINT vigilante_pkey;
       public            postgres    false    209            �           2606    16578    factura fk_cod_serv    FK CONSTRAINT     z   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_cod_serv FOREIGN KEY (cod_serv) REFERENCES public.servicio(codigo);
 =   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_cod_serv;
       public          postgres    false    215    212    3194            �           2606    16598 $   detalle_servicio fk_detalle_cod_serv    FK CONSTRAINT     �   ALTER TABLE ONLY public.detalle_servicio
    ADD CONSTRAINT fk_detalle_cod_serv FOREIGN KEY (serv_cod) REFERENCES public.servicio(codigo);
 N   ALTER TABLE ONLY public.detalle_servicio DROP CONSTRAINT fk_detalle_cod_serv;
       public          postgres    false    216    212    3194            �           2606    16593 !   detalle_servicio fk_detalle_id_vg    FK CONSTRAINT     �   ALTER TABLE ONLY public.detalle_servicio
    ADD CONSTRAINT fk_detalle_id_vg FOREIGN KEY (id_vg) REFERENCES public.vigilante(id_vig);
 K   ALTER TABLE ONLY public.detalle_servicio DROP CONSTRAINT fk_detalle_id_vg;
       public          postgres    false    216    209    3192            �           2606    16583    factura fk_factura_cod_alquiler    FK CONSTRAINT     �   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_factura_cod_alquiler FOREIGN KEY (cod_alq) REFERENCES public.alquiler(codigo_alq);
 I   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_factura_cod_alquiler;
       public          postgres    false    3198    214    215            �           2606    16573    factura fk_factura_id_client    FK CONSTRAINT     �   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_factura_id_client FOREIGN KEY (id_client) REFERENCES public.cliente(id_cliente);
 F   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_factura_id_client;
       public          postgres    false    215    213    3196            �           2606    16516    horario fk_horario_id_vg    FK CONSTRAINT     }   ALTER TABLE ONLY public.horario
    ADD CONSTRAINT fk_horario_id_vg FOREIGN KEY (id_vg) REFERENCES public.vigilante(id_vig);
 B   ALTER TABLE ONLY public.horario DROP CONSTRAINT fk_horario_id_vg;
       public          postgres    false    210    3192    209            �           2606    16524    nomina fk_nomina_id_vg    FK CONSTRAINT     {   ALTER TABLE ONLY public.nomina
    ADD CONSTRAINT fk_nomina_id_vg FOREIGN KEY (id_vg) REFERENCES public.vigilante(id_vig);
 @   ALTER TABLE ONLY public.nomina DROP CONSTRAINT fk_nomina_id_vg;
       public          postgres    false    209    3192    211            �           2606    16563    alquiler fk_servicio_cod    FK CONSTRAINT     ~   ALTER TABLE ONLY public.alquiler
    ADD CONSTRAINT fk_servicio_cod FOREIGN KEY (id_serv) REFERENCES public.servicio(codigo);
 B   ALTER TABLE ONLY public.alquiler DROP CONSTRAINT fk_servicio_cod;
       public          postgres    false    214    3194    212            �           2606    16548    servicio fk_servicio_id_client    FK CONSTRAINT     �   ALTER TABLE ONLY public.servicio
    ADD CONSTRAINT fk_servicio_id_client FOREIGN KEY (client_id) REFERENCES public.cliente(id_cliente);
 H   ALTER TABLE ONLY public.servicio DROP CONSTRAINT fk_servicio_id_client;
       public          postgres    false    3196    212    213                  x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �     