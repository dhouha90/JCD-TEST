package com.example.dchikhaoui.monmap.Model

data  class Bike( val title: String,
                  val subtitle: String) {

    override fun toString(): String {
        return "Bike(title='$title', subtitle='$subtitle')"
    }
}