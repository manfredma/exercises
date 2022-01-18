package com.example.springkotlin
 
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
 
@SpringBootApplication
open class SpringKotlinApplication
 
fun main(args: Array<String>) {
    SpringApplication.run(SpringKotlinApplication::class.java, *args)
}