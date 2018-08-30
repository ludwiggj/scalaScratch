package org.ludwiggj.cake.example2

class RESTService extends MySQLStorageComponent with GravatarComponent {
  type Config = MySQLConfig with GravatarConfig

   object config extends MySQLConfig with GravatarConfig {

    val mysqlHost = "localhost"
    val mysqlPort = 3336
    val token = "1234cafebabe"
  }
}