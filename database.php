<?php
	echo "---------- SQL Info ---------". "\xA";
	$servername = readline("<- Host: ");
	$username = readline("<- Username: ");
	$password = readline("<- Password: ");
	echo "-----------------------------". "\xA";

	$conn = new mysqli($servername, $username, $password);

	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}

	echo "---------- Medievo ---------" . "\xA";

	$create_db = "CREATE DATABASE test";
	if ($conn->query($create_db) === TRUE) {
	    echo "-> Database created successfully." . "\xA";
	} else {
	    echo "-> Error creating database: " . $conn->error . "\xA";
	}

	$create_player_data = "CREATE TABLE `test`.`player_data` 
			( 
			`uuid` VARCHAR(50) NOT NULL ,
			`name` VARCHAR(16) NOT NULL ,
			`gold` INT NOT NULL ,
			`clan` VARCHAR(16) NOT NULL 
			) 
			ENGINE = InnoDB;
			";
	if ($conn->query($create_player_data) === TRUE) {
	    echo "-> player_data table created successfully." . "\xA";
	} else {
	    echo "-> Error creating player_data table: " . $conn->error . "\xA";
	}

	$create_clans_data = "CREATE TABLE `test`.`clans_data` 
			( 
			`name` VARCHAR(16) NOT NULL , 
			`leader_uuid` VARCHAR(50) NOT NULL , 
			`leader_name` VARCHAR(16) NOT NULL 
			) 
			ENGINE = InnoDB;
			";
	if ($conn->query($create_clans_data) === TRUE) {
	    echo "-> clans_data table created successfully." . "\xA";
	} else {
	    echo "-> Error creating clans_data table " . $conn->error . "\xA";
	}

	$conn->close();
	echo "-----------------------------" . "\xA";
?> 