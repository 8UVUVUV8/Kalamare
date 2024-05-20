<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\Kolcsonzok;

class Kolcsonzesek extends Model
{
    use HasFactory;

    protected $table = "kolcsonzesek";
    protected $primaryKey = "Kolcsonzes_id";

    public $timestamps = false;

    protected $fillable =[
        "kolcsonzo_id",
        "iro",
        "mufaj",
        "cim"
    ];

    public function kolcsonzok(){                                   //harmadiki primary key a fö táblában ami hez belogsTo ez a tábla
        return $this->belongsTo(Kolcsonzok::class, "kolcsonzo_id", "kolcsonzo_id");
    }                                            //második paraméter a FK(a kulcs ami a fö táblára vonatkozik ebben a táblában)

}
