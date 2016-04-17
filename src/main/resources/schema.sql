CREATE TABLE goal (
uuid UUID NOT NULL,
name TEXT,
userid TEXT,
created_at TEXT,
week INTEGER,
done_at BOOLEAN
PRIMARY KEY (uuid)
);




--       private final UUID uuid;

--       private final String name;

--       private final String user;

--       private final LocalDateTime createdAt;

--       private final int goalWeek;
