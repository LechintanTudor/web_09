DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS picture CASCADE;
DROP TABLE IF EXISTS vote CASCADE;
DROP PROCEDURE IF EXISTS usp_submit_vote;
DROP PROCEDURE IF EXISTS usp_remove_vote;

CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE picture (
    id SERIAL PRIMARY KEY,
    file_path VARCHAR(255) NOT NULL,
    account_id INT NOT NULL REFERENCES account(id),
    total_votes INT NOT NULL DEFAULT 0
);

CREATE TABLE vote (
    account_id INT NOT NULL,
    picture_id INT NOT NULL,
    value INT NOT NULL CHECK (value BETWEEN 1 AND 5),
    PRIMARY KEY (account_id, picture_id)
);

CREATE PROCEDURE usp_submit_vote(a_account_id INT, a_picture_id INT, a_vote_value INT) AS $$
BEGIN
    INSERT INTO vote (account_id, picture_id, value)
    VALUES (a_account_id, a_picture_id, a_vote_value);

    UPDATE picture SET total_votes = total_votes + a_vote_value
    WHERE id = a_picture_id;
END;
$$ LANGUAGE plpgsql;

CREATE PROCEDURE usp_remove_vote(a_account_id INT, a_picture_id INT) AS $$
DECLARE
    vote_value INT := NULL;
BEGIN
    vote_value := (
        SELECT value FROM vote
        WHERE account_id = a_account_id AND picture_id = a_picture_id
    );

    IF vote_value IS NOT NULL THEN
        DELETE FROM vote
        WHERE account_id = a_account_id AND picture_id = a_picture_id;

        UPDATE picture SET total_votes = total_votes - vote_value
        WHERE (id, account_id) = (a_picture_id, account_id);
    END IF;
END;
$$ LANGUAGE plpgsql;
