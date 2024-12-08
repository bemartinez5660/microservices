CREATE TABLE IF NOT EXISTS tbl_categories (
                                id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tbl_products (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              description VARCHAR(255),
                              stock INT NOT NULL,
                              price DECIMAL(10, 2) NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              created_at DATE NOT NULL,
                              category_id BIGINT,
                              FOREIGN KEY (category_id) REFERENCES tbl_categories(id)
);