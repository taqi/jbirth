drop keyspace jbirth;
CREATE KEYSPACE jbirth with placement_strategy = 'org.apache.cassandra.locator.SimpleStrategy' and strategy_options = {replication_factor:1};
use jbirth;
CREATE COLUMN FAMILY users
with comparator=UTF8Type 
AND key_validation_class=UTF8Type 
AND default_validation_class=UTF8Type
AND column_metadata = [
	{column_name:login, validation_class: UTF8Type,index_type: KEYS}
]; 

CREATE COLUMN FAMILY users_groups
with comparator=UTF8Type 
AND key_validation_class=UTF8Type 
AND default_validation_class=UTF8Type;

drop keyspace jbirth_test;
CREATE KEYSPACE jbirth_test with placement_strategy = 'org.apache.cassandra.locator.SimpleStrategy' and strategy_options = {replication_factor:1};
use jbirth_test;

CREATE COLUMN FAMILY users
with comparator=UTF8Type 
AND key_validation_class=UTF8Type 
AND default_validation_class=UTF8Type
AND column_metadata = [
	{column_name:login, validation_class: UTF8Type,index_type: KEYS}
]; 

CREATE COLUMN FAMILY users_groups
with comparator=UTF8Type 
AND key_validation_class=UTF8Type 
AND default_validation_class=UTF8Type;

use jbirth;
set users['1']['login'] = 'jbirth';
set users['1']['password'] = 'jbirth';
set users['1']['email'] = 'jbirth@example.com';
set users['1']['name'] = 'jbirth';
set users['1']['age'] = int(42);

set users_groups['1']['ROLE_USER'] = '';

