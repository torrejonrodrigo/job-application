CREATE DATABASE productos
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


-- Table: public.producto

-- DROP TABLE IF EXISTS public.producto;

CREATE TABLE IF NOT EXISTS public.producto
(
    id bigint NOT NULL DEFAULT nextval('producto_id_seq'::regclass),
    descripcion character varying(255) COLLATE pg_catalog."default",
    nombre character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT producto_pkey PRIMARY KEY (id),
    CONSTRAINT uk_9su14n91mtgcg5ehl658v4afx UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.producto
    OWNER to postgres;

-- Table: public.version

-- DROP TABLE IF EXISTS public.version;

CREATE TABLE IF NOT EXISTS public.version
(
    id bigint NOT NULL DEFAULT nextval('version_id_seq'::regclass),
    producto_id bigint NOT NULL DEFAULT nextval('version_producto_id_seq'::regclass),
    numero_version character varying(50) COLLATE pg_catalog."default" NOT NULL,
    fecha_lanzamiento date NOT NULL,
    estado estado_enum NOT NULL,
    notas text COLLATE pg_catalog."default",
    CONSTRAINT version_pkey PRIMARY KEY (id),
    CONSTRAINT version_producto_id_fkey FOREIGN KEY (producto_id)
        REFERENCES public.producto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT version_numero_version_format CHECK (numero_version::text ~ '^(\d+)\.(\d+)\.(\d+)$'::text),
    CONSTRAINT version_fecha_lanzamiento_valid CHECK (fecha_lanzamiento >= CURRENT_DATE)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.version
    OWNER to postgres;
-- Index: idx_version_producto_id

-- DROP INDEX IF EXISTS public.idx_version_producto_id;

CREATE INDEX IF NOT EXISTS idx_version_producto_id
    ON public.version USING btree
    (producto_id ASC NULLS LAST)
    TABLESPACE pg_default;