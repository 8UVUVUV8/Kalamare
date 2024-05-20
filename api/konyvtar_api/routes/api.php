<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\KolcsonzesekController;
use App\Http\Controllers\KolcsonzokController;



Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');


Route::get('/Kolcsonzesek', [KolcsonzesekController::class, "getKolcsonzesek"] );

Route::get('/Kolcsonzesek/{id}', [KolcsonzesekController::class, "getOneKolcsonzesek" ]);

Route::post('/Kolcsonzesek', [KolcsonzesekController::class, "postKolcsonzesek"]);

Route::delete('/Kolcsonzesek/{id}', [KolcsonzesekController::class, "deleteKolcsonzesek" ]);



Route::put('/Kolcsonzesek', [KolcsonzesekController::class, "putKolcsonzesek"]);



Route::get('/Kolcsonzok', [KolcsonzokController::class, "getKolcsonzok"] );

//Route::get("/Kolcsonzesek", [TypeController::class,"addType"]);