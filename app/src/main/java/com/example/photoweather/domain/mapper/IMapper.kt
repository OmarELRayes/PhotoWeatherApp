package com.example.photoweather.domain.mapper

interface IMapper<From, To> {
    fun map(inputFormat: From): To
    fun reverseMap(inputFormat: To): From
}
