<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Kolcsonzesek;
use App\Http\Controllers\KolcsonzokController;

class KolcsonzesekController extends Controller

{
    function getKolcsonzesek(){

                                    // Olyan mint a join két tábla kapcsolatban lévö adatait kérdezi le hasMany/belongsTo
        $kolcsonzesek = Kolcsonzesek::with("kolcsonzok")->get();
    
        return response()->json($kolcsonzesek, 200, ['Content-Type' => 'application/json;charset=UTF-8', 'Charset' => 'utf-8'], JSON_UNESCAPED_UNICODE);
    }

    function getOneKolcsonzesek($id){
        
        $kolcsozes = Kolcsonzesek::with("kolcsonzok")->where("Kolcsonzes_id", $id)->first();
        
        return $kolcsozes;
    }

    function deleteKolcsonzesek($id){
        $kolcsonzes =  Kolcsonzesek::where("Kolcsonzes_id", $id)->delete();

        return $kolcsonzes;
    }

    function postKolcsonzesek(Request $req){
        $r = $req->all();
        $r["kolcsonzo_id"] = (new KolcsonzokController)->getKolcsonzoId($r["kolcsonzo_id"]);

        $kolcsonzes = new Kolcsonzesek;
            $kolcsonzes->kolcsonzo_id = $r["kolcsonzo_id"];
            $kolcsonzes->iro = $r["iro"];
            $kolcsonzes->mufaj = $r["mufaj"];
            $kolcsonzes->cim = $r["cim"];
        $kolcsonzes->save();


        return $r;
    }


    function putKolcsonzesek(Request $req){
        $req = $req->all();
        $req["kolcsonzo_id"] = (new KolcsonzokController)->getKolcsonzoId($req["kolcsonzo_id"]);

        $kolcsonzes = Kolcsonzesek::where("Kolcsonzes_id", $req["kolcsonzes_id"])->update([
            "kolcsonzo_id" => $req["kolcsonzo_id"],
            "iro" => $req["iro"],
            "cim" => $req["cim"],
            "mufaj" => $req["mufaj"]
        ]);

        
        return $this->getOneKolcsonzesek($req["kolcsonzes_id"]);
    }



}
