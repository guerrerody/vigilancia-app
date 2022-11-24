CREATE SEQUENCE IF NOT EXISTS tipo_cliente_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE tipo_cliente (
    id INTEGER NOT NULL DEFAULT nextval('tipo_cliente_id_seq'::regclass),
    nombre VARCHAR(100) NOT NULL,
	CONSTRAINT pk_tipo_cliente PRIMARY KEY (id)    
);

INSERT INTO tipo_cliente (nombre) VALUES ('Urbanización');
INSERT INTO tipo_cliente (nombre) VALUES ('Edificio');
INSERT INTO tipo_cliente (nombre) VALUES ('Tienda');

CREATE SEQUENCE IF NOT EXISTS cliente_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE cliente (
    id INTEGER NOT NULL DEFAULT nextval('cliente_id_seq'::regclass),
    tipo_cliente_id INTEGER NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    localidad VARCHAR(100),
    direccion VARCHAR(150),
    nombre_contac VARCHAR(100),
    telf_contac VARCHAR(12),
    status INTEGER NOT NULL DEFAULT 0,
	CONSTRAINT pk_cliente PRIMARY KEY (id),
	CONSTRAINT fk_cliente_tipo_cliente FOREIGN KEY (tipo_cliente_id) REFERENCES tipo_cliente(id),
	CONSTRAINT uk_cedula UNIQUE (cedula)
);

CREATE SEQUENCE IF NOT EXISTS servicio_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE servicio (
    id INTEGER NOT NULL DEFAULT nextval('servicio_id_seq'::regclass),
    client_id INTEGER NOT NULL,
    fecha DATE,
    descr VARCHAR(150),
    turno TIMESTAMP WITHOUT TIME ZONE,
    vig_hab INTEGER,
    status INTEGER,
    CONSTRAINT pk_servicio PRIMARY KEY (id),
	CONSTRAINT fk_servicio_id_client FOREIGN KEY (client_id) REFERENCES cliente(id)
);

CREATE SEQUENCE IF NOT EXISTS alquiler_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE alquiler (
    id INTEGER NOT NULL DEFAULT nextval('alquiler_id_seq'::regclass),
    id_serv INTEGER,
    fechain DATE,
    fechafin DATE,
    descr VARCHAR(255),
    cant INTEGER,
    hruso INTEGER,
    costou NUMERIC(10,2),
    costomant NUMERIC(10,2),
    status INTEGER,
    CONSTRAINT pk_alquiler PRIMARY KEY (id),
    CONSTRAINT fk_servicio_cod FOREIGN KEY (id_serv) REFERENCES servicio(id)
);

CREATE SEQUENCE IF NOT EXISTS vigilante_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE vigilante (
    id INTEGER NOT NULL DEFAULT nextval('vigilante_id_seq'::regclass),
    cedula VARCHAR(12) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fec_nac DATE NOT NULL,
    correo VARCHAR(100),
    telf VARCHAR(12),
    fec_ing DATE NOT NULL DEFAULT CURRENT_DATE,
    status INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT pk_vigilante PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS horario_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE horario (
	id INTEGER NOT NULL DEFAULT nextval('horario_id_seq'::regclass),
    id_vg INTEGER NOT NULL,
    fechain DATE,
    fechafin DATE,
    horain TIME WITHOUT TIME ZONE,
    horafin TIME WITHOUT TIME ZONE,
    faltas INTEGER,
    just INTEGER,
    CONSTRAINT pk_horario PRIMARY KEY (id),
    CONSTRAINT fk_horario_id_vg FOREIGN KEY (id_vg) REFERENCES vigilante(id)
);

CREATE SEQUENCE IF NOT EXISTS nomina_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE nomina (
    id INTEGER NOT NULL DEFAULT nextval('nomina_id_seq'::regclass),
    id_vg INTEGER NOT NULL,
    fecha DATE,
    descr VARCHAR(255),
    diasextra INTEGER,
    hrextra INTEGER,
    diasfaltas INTEGER,
    sueldob NUMERIC(10,2),
    pagoextra NUMERIC(10,2),
    deduccion NUMERIC(10,2),
    CONSTRAINT pk_nomina PRIMARY KEY (id),
    CONSTRAINT fk_nomina_id_vg FOREIGN KEY (id_vg) REFERENCES vigilante(id)
);

CREATE TABLE detalle_servicio (
    id_vg INTEGER NOT NULL,
    serv_cod INTEGER NOT NULL,
    resumen TEXT,
    CONSTRAINT pk_detalle_servicio PRIMARY KEY (id_vg, serv_cod),
    CONSTRAINT fk_detalle_cod_serv FOREIGN KEY (serv_cod) REFERENCES servicio(id),
	CONSTRAINT fk_detalle_id_vg FOREIGN KEY (id_vg) REFERENCES vigilante(id)    
);

CREATE SEQUENCE IF NOT EXISTS factura_id_seq
    INCREMENT 1
    START 10000
    MINVALUE 10000
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE factura (
    id INTEGER NOT NULL DEFAULT nextval('factura_id_seq'::regclass),
    id_client INTEGER,
    cod_serv INTEGER,
    cod_alq INTEGER,
    fecha_pago DATE,
    tipo_pago INTEGER,
    descr VARCHAR(255),
    monto_ser NUMERIC(10,2),
    monto_alq NUMERIC(10,2),
    total NUMERIC(10,2),
    status INTEGER,
	CONSTRAINT pk_factura PRIMARY KEY (id),
	CONSTRAINT fk_cod_serv FOREIGN KEY (cod_serv) REFERENCES servicio(id),
	CONSTRAINT fk_factura_cod_alquiler FOREIGN KEY (cod_alq) REFERENCES alquiler(id),
	CONSTRAINT fk_factura_id_client FOREIGN KEY (id_client) REFERENCES cliente(id)    
);
