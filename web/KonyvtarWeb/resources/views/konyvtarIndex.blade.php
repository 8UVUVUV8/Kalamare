

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="{{asset('assets/css/index.css')}}">
</head>
<body>
        <h1>Library</h1>
        <div class="kepek">
            
            <button type="button" >
                <a class="nav-link" href="{{ url('/kolcsonzesek') }}">
                    <img id="kep1" src="{{asset('assets/images/Book.png')}}" alt="" width="300" height="300">
                    <img id="kep2" src="{{asset('assets/images/Book.png')}}" alt="" width="300" height="300">
                </a>
            </button>
        </div>
</body>
</html>