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

-- Name: restaurants; Type: TABLE; Schema: public; Owner: morgaface; Tablespace:

--

CREATE TABLE restaurants (
    id integer NOT NULL,
    name character varying,
    address character varying,
    phone character varying,
    website character varying,
    yelp character varying,
    hours character varying,
    quadrant_id integer
);


ALTER TABLE restaurants OWNER TO morgaface;

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

-- Name: restaurants_tags; Type: TABLE; Schema: public; Owner: morgaface; Tablespace:

--

CREATE TABLE restaurants_tags (
    id integer NOT NULL,
    restaurant_id integer,
    tag_id integer
);


ALTER TABLE restaurants_tags OWNER TO morgaface;

--
-- Name: restaurants_tags_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE restaurants_tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_tags_id_seq OWNER TO morgaface;

--
-- Name: restaurants_tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE restaurants_tags_id_seq OWNED BY restaurants_tags.id;


--
-- Name: tags; Type: TABLE; Schema: public; Owner: morgaface; Tablespace:
--

CREATE TABLE tags (
    id integer NOT NULL,
    description character varying
);


ALTER TABLE tags OWNER TO morgaface;

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: morgaface
--

CREATE SEQUENCE tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tags_id_seq OWNER TO morgaface;

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: morgaface
--

ALTER SEQUENCE tags_id_seq OWNED BY tags.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


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

ALTER TABLE ONLY restaurants ALTER COLUMN id SET DEFAULT nextval('restaurants_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY restaurants_tags ALTER COLUMN id SET DEFAULT nextval('restaurants_tags_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: morgaface
--

ALTER TABLE ONLY tags ALTER COLUMN id SET DEFAULT nextval('tags_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY categories (id, type) FROM stdin;
1	coffee
2	bakery
3	breakfast
4	foodcart
5	lunch
6	dinner
7	dessert
8	happyhour
9	drinks
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('categories_id_seq', 9, true);


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
2	1	1
3	2	4
4	2	5
5	3	2
6	3	4
7	3	3
8	4	4
9	4	5
10	5	4
11	5	5
12	5	6
13	6	7
14	7	4
15	8	8
16	8	2
17	8	4
18	9	1
19	9	8
20	10	1
\.


--
-- Name: restaurant_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurant_category_id_seq', 20, true);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY restaurants (id, name, address, phone, website, yelp, hours, quadrant_id) FROM stdin;
1	Junior's Cafe	1742 SE 12th Avenue Portland, OR 97214	503 467 4971	http://www.juniorscafepdx.com/	http://www.yelp.com/biz/juniors-cafe-portland	8:00 AM - 2:30 PM	\N
2	Lúc Lác Vietnamese Kitchen	835 Sw 2nd Ave Portland, OR 97204	503-222-0047	http://luclackitchen.com/	http://www.yelp.com/biz/l%C3%BAc-l%C3%A1c-portland-3	11:00 am - 2:30 pm && 4:00 pm - 12:00 am	\N
3	Lardo	1212 SE Hawthorne St Portland, OR 97214	(503) 234-7786	http://lardosandwiches.com/	http://www.yelp.com/biz/lardo-portland	11:00 am - 10:00 pm	\N
4	Irving Street Kitchen	701 NW 13th St Portland, OR 97209	(503) 343-9440	irvingstreetkitchen.com	http://www.yelp.com/biz/irving-st-kitchen-portland-2	10:00am- 2:30pm && 5:30pm- 10:00pm	\N
5	Santería	703 SW Ankeny St Portland, OR 97205	(503) 956-7624	thesanteria.com	http://www.yelp.com/biz/santer%C3%ADa-portland-2	11am to 2am	\N
6	Barista	539 NW 13th Ave Portland, OR 97217	(503) 274-1211	baristapdx.com	http://www.yelp.com/biz/barista-portland	6am to 6pm	\N
7	Dove Vivi	http://www.yelp.com/biz/dove-vivi-portland	(503) 239-4444	dovevivipizza.com	http://www.yelp.com/biz/dove-vivi-portland	4pm to 10pm	\N
8	Roman Candle Baking Co.	3377 SE Division St Portland, OR 97202	(971) 302-6605	http://www.romancandlebaking.com/	http://www.yelp.com/biz/roman-candle-portland	8:30am to 4pm && 5pm to 9pm	\N
9	Little T Baker	2600 SE Division St Portland, OR 97202	(503) 238-3458	littletbaker.com	http://www.yelp.com/biz/little-t-american-baker-portland	7am-5pm	\N
10	Jam on Hawthorne	2239 SE Hawthorne Blvd Portland, OR 97214	(503) 234-4790	jamonhawthorne.com	http://www.yelp.com/biz/jam-on-hawthorne-portland	7:30am to 3pm	\N
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurants_id_seq', 10, true);


--
-- Data for Name: restaurants_tags; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY restaurants_tags (id, restaurant_id, tag_id) FROM stdin;
\.


--
-- Name: restaurants_tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('restaurants_tags_id_seq', 1, false);


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: morgaface
--

COPY tags (id, description) FROM stdin;
\.


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: morgaface
--

SELECT pg_catalog.setval('tags_id_seq', 1, false);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace:
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


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

-- Name: restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace:

--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--

-- Name: restaurants_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace:

--

ALTER TABLE ONLY restaurants_tags
    ADD CONSTRAINT restaurants_tags_pkey PRIMARY KEY (id);


--

-- Name: tags_pkey; Type: CONSTRAINT; Schema: public; Owner: morgaface; Tablespace:

--

ALTER TABLE ONLY tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


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
