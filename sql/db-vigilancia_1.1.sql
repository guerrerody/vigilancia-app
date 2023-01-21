ALTER TABLE factura
DROP COLUMN tipo_pago;
ALTER TABLE factura
RENAME COLUMN fecha_pago TO fec_pago;
ALTER TABLE factura
RENAME COLUMN monto_ser TO subtotal;
ALTER TABLE factura
RENAME COLUMN monto_alq TO monto_total;
ALTER TABLE factura
RENAME COLUMN total TO iva;

ALTER TABLE servicio
ADD COLUMN costo NUMERIC(10,2);

ALTER TABLE nomina
RENAME COLUMN diasextra TO dias_trab;
ALTER TABLE nomina
RENAME COLUMN hrextra TO horas_extra;
ALTER TABLE nomina
RENAME COLUMN diasfaltas TO dias_falta;
ALTER TABLE nomina
RENAME COLUMN sueldob TO sueldo_base;
ALTER TABLE nomina
RENAME COLUMN pagoextra TO pago_extra;

ALTER TABLE turno
DROP COLUMN id_horario;
ALTER TABLE turno
ADD COLUMN hor_in TIME WITHOUT TIME ZONE NOT NULL;
ALTER TABLE turno
ADD COLUMN hor_fin TIME WITHOUT TIME ZONE NOT NULL;

ALTER TABLE turno ALTER COLUMN fec_in SET NOT NULL;
ALTER TABLE turno ALTER COLUMN fec_fin SET NOT NULL;

