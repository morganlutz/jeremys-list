--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE categories (
    id integer NOT NULL,
    type character varying
);


ALTER TABLE categories OWNER TO morgaface;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO morgaface;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: dietary_restrictions; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE dietary_restrictions (
    id integer NOT NULL,
    description character varying
);


ALTER TABLE dietary_restrictions OWNER TO morgaface;

--
-- Name: dietary_restrictions_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE dietary_restrictions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dietary_restrictions_id_seq OWNER TO morgaface;

--
-- Name: dietary_restrictions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE dietary_restrictions_id_seq OWNED BY dietary_restrictions.id;


--
-- Name: quadrants; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE quadrants (
    id integer NOT NULL,
    quadrant character varying
);


ALTER TABLE quadrants OWNER TO morgaface;

--
-- Name: quadrants_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE quadrants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE quadrants_id_seq OWNER TO morgaface;

--
-- Name: quadrants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE quadrants_id_seq OWNED BY quadrants.id;


--
-- Name: restaurant_category; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE restaurant_category (
    id integer NOT NULL,
    restaurant_id integer,
    category_id integer
);


ALTER TABLE restaurant_category OWNER TO morgaface;

--
-- Name: restaurant_category_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE restaurant_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurant_category_id_seq OWNER TO morgaface;

--
-- Name: restaurant_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE restaurant_category_id_seq OWNED BY restaurant_category.id;


--
-- Name: restaurant_location; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE restaurant_location (
    id integer NOT NULL,
    restaurant_id integer,
    quadrant_id integer
);


ALTER TABLE restaurant_location OWNER TO morgaface;

--
-- Name: restaurant_location_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE restaurant_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurant_location_id_seq OWNER TO morgaface;

--
-- Name: restaurant_location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE restaurant_location_id_seq OWNED BY restaurant_location.id;


--
-- Name: restaurants; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE restaurants (
    id integer NOT NULL,
    name character varying,
    address character varying,
    phone character varying,
    website character varying,
    yelp character varying
);


ALTER TABLE restaurants OWNER TO morgaface;

--
-- Name: restaurants_for_diets; Type: TABLE; Schema: public; Owner: morgaface; Tablespace: 
--

CREATE TABLE restaurants_for_diets (
    id integer NOT NULL,
    restaurant_id integer,
    dietary_id integer
);


ALTER TABLE restaurants_for_diets OWNER TO morgaface;

--
-- Name: restaurants_for_diets_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE restaurants_for_diets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_for_diets_id_seq OWNER TO morgaface;

--
-- Name: restaurants_for_diets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE restaurants_for_diets_id_seq OWNED BY restaurants_for_diets.id;


--
-- Name: restaurants_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE restaurants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_id_seq OWNER TO morgaface;

--
-- Name: restaurants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE restaurants_id_seq OWNED BY restaurants.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY dietary_restrictions ALTER COLUMN id SET DEFAULT nextval('dietary_restrictions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY quadrants ALTER COLUMN id SET DEFAULT nextval('quadrants_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY restaurant_category ALTER COLUMN id SET DEFAULT nextval('restaurant_category_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY restaurant_location ALTER COLUMN id SET DEFAULT nextval('restaurant_location_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY restaurants ALTER COLUMN id SET DEFAULT nextval('restaurants_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY restaurants_for_diets ALTER COLUMN id SET DEFAULT nextval('restaurants_for_diets_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY categories (id, type) FROM stdin;
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('categories_id_seq', 1, false);


--
-- Data for Name: dietary_restrictions; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY dietary_restrictions (id, description) FROM stdin;
\.


--
-- Name: dietary_restrictions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('dietary_restrictions_id_seq', 1, false);


--
-- Data for Name: quadrants; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY quadrants (id, quadrant) FROM stdin;
\.


--
-- Name: quadrants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('quadrants_id_seq', 1, false);


--
-- Data for Name: restaurant_category; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY restaurant_category (id, restaurant_id, category_id) FROM stdin;
\.


--
-- Name: restaurant_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurant_category_id_seq', 1, false);


--
-- Data for Name: restaurant_location; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY restaurant_location (id, restaurant_id, quadrant_id) FROM stdin;
\.


--
-- Name: restaurant_location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurant_location_id_seq', 1, false);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY restaurants (id, name, address, phone, website, yelp) FROM stdin;
\.


--
-- Data for Name: restaurants_for_diets; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY restaurants_for_diets (id, restaurant_id, dietary_id) FROM stdin;
\.


--
-- Name: restaurants_for_diets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurants_for_diets_id_seq', 1, false);


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurants_id_seq', 1, false);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: dietary_restrictions_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY dietary_restrictions
    ADD CONSTRAINT dietary_restrictions_pkey PRIMARY KEY (id);


--
-- Name: quadrants_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY quadrants
    ADD CONSTRAINT quadrants_pkey PRIMARY KEY (id);


--
-- Name: restaurant_category_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY restaurant_category
    ADD CONSTRAINT restaurant_category_pkey PRIMARY KEY (id);


--
-- Name: restaurant_location_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY restaurant_location
    ADD CONSTRAINT restaurant_location_pkey PRIMARY KEY (id);


--
-- Name: restaurants_for_diets_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY restaurants_for_diets
    ADD CONSTRAINT restaurants_for_diets_pkey PRIMARY KEY (id);


--
-- Name: restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace: 
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: morgaface
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM morgaface;
GRANT ALL ON SCHEMA public TO morgaface;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

