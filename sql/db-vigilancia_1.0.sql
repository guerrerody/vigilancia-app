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
	CONSTRAINT fk_cliente_tipo_cliente FOREIGN KEY (tipo_cliente_id) REFERENCES tipo_cliente(id)
);

CREATE SEQUENCE IF NOT EXISTS tipo_alquiler_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE tipo_alquiler (
    id INTEGER NOT NULL DEFAULT nextval('tipo_alquiler_id_seq'::regclass),
    nombre VARCHAR(100) NOT NULL,
    costo_uso NUMERIC(10,2), -- Costo de Uso Mensual
    costo_mant NUMERIC(10,2), -- Costo de Mantenimiento Mensual
	CONSTRAINT pk_tipo_alquiler PRIMARY KEY (id)
);

INSERT INTO tipo_alquiler (nombre) VALUES ('Bicicleta');
INSERT INTO tipo_alquiler (nombre) VALUES ('Radio');

CREATE SEQUENCE IF NOT EXISTS servicio_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE servicio (
    id INTEGER NOT NULL DEFAULT nextval('servicio_id_seq'::regclass),
    client_id INTEGER NOT NULL,
    fec_in DATE NOT NULL,
	fec_fin DATE NOT NULL,
    descr VARCHAR(150),
	status INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT pk_servicio PRIMARY KEY (id),
	CONSTRAINT fk_servicio_cliente_id FOREIGN KEY (client_id) REFERENCES cliente(id)
);

CREATE TABLE servicio_extra (
    servicio_id INTEGER NOT NULL,
    tipo_alquiler_id INTEGER NOT NULL,
    cant INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT pk_servicio_extra PRIMARY KEY (servicio_id, tipo_alquiler_id),
	CONSTRAINT fk_servicio_id FOREIGN KEY (servicio_id) REFERENCES servicio(id),
    CONSTRAINT fk_tipo_alquiler_id FOREIGN KEY (tipo_alquiler_id) REFERENCES tipo_alquiler(id)
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
    CONSTRAINT pk_vigilante PRIMARY KEY (id),
	CONSTRAINT uk_cedula UNIQUE (cedula)
);

CREATE SEQUENCE IF NOT EXISTS horario_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE horario (
	id INTEGER NOT NULL DEFAULT nextval('horario_id_seq'::regclass),
    vigilante_id INTEGER NOT NULL,
    fec_in DATE,
    fec_fin DATE,
    CONSTRAINT pk_horario PRIMARY KEY (id),
    CONSTRAINT fk_horario_vigilante_id FOREIGN KEY (vigilante_id) REFERENCES vigilante(id)
);

CREATE SEQUENCE IF NOT EXISTS turno_id_seq
   	INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE turno (
    id INTEGER NOT NULL DEFAULT nextval('turno_id_seq'::regclass),
    id_horario INTEGER NOT NULL,
    fec_in TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    fec_fin TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    falta BOOLEAN NOT NULL DEFAULT FALSE,
    just TEXT,
    CONSTRAINT pk_turno PRIMARY KEY (id),
    CONSTRAINT fk_turno_id_h FOREIGN KEY (id_horario) REFERENCES horario(id)
);

CREATE SEQUENCE IF NOT EXISTS nomina_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE nomina (
    id INTEGER NOT NULL DEFAULT nextval('nomina_id_seq'::regclass),
    vigilante_id INTEGER NOT NULL,
    fecha DATE,
    descr VARCHAR(255),
    diasextra INTEGER,
    hrextra INTEGER,
    diasfaltas INTEGER,
    sueldob NUMERIC(10,2),
    pagoextra NUMERIC(10,2),
    deduccion NUMERIC(10,2),
    CONSTRAINT pk_nomina PRIMARY KEY (id),
    CONSTRAINT fk_nomina_vigilante_id FOREIGN KEY (vigilante_id) REFERENCES vigilante(id)
);

CREATE TABLE detalle_servicio (
    vigilante_id INTEGER NOT NULL,
    servicio_id INTEGER NOT NULL,
	turno_id INTEGER NOT NULL,
    resumen TEXT,
    CONSTRAINT pk_detalle_servicio PRIMARY KEY (vigilante_id, servicio_id, turno_id),
    CONSTRAINT fk_detalle_servicio_id FOREIGN KEY (servicio_id) REFERENCES servicio(id),
	CONSTRAINT fk_detalle_vigilante_id FOREIGN KEY (vigilante_id) REFERENCES vigilante(id),
	CONSTRAINT fk_detalle_id_t FOREIGN KEY (turno_id) REFERENCES turno(id)
);

CREATE SEQUENCE IF NOT EXISTS factura_id_seq
    INCREMENT 1
    START 10000
    MINVALUE 10000
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE factura (
    id INTEGER NOT NULL DEFAULT nextval('factura_id_seq'::regclass),
    servicio_id INTEGER,
    fecha_pago DATE,
    tipo_pago INTEGER,
    descr VARCHAR(255),
    monto_ser NUMERIC(10,2),
    monto_alq NUMERIC(10,2),
    total NUMERIC(10,2),
    status INTEGER,
	CONSTRAINT pk_factura PRIMARY KEY (id),
	CONSTRAINT fk_servicio_id FOREIGN KEY (servicio_id) REFERENCES servicio(id) 
);
