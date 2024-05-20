<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Kolcsonzok;

class KolcsonzokController extends Controller
{

    function getKolcsonzok (){
        $kolcsonzok = Kolcsonzok::with("kolcsonzesek")->get();
        //$kolcsonzok = Kolcsonzok::all();

        return response()->json($kolcsonzok, 202, ['Content-Type' => 'application/json;charset=UTF-8', 'Charset' => 'utf-8'], JSON_UNESCAPED_UNICODE);
    }

    function getKolcsonzoId($name){
        $kolcsonzoId = Kolcsonzok::where("nev", $name)->first()->kolcsonzo_id;

        return $kolcsonzoId; 
    }
}
