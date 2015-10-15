(function() {
  var geocoder;
  var map;
  var initialize = function() {
    var latlng = new google.maps.LatLng(45.5204527, -122.673812);
    var mapOptions = {
      zoom: 15,
      center: latlng
    }
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
  }

  var restaurantArray = document.getElementsByClassName("restaurant-address");




  function codeAddress(address) {
    // debugger;
    geocoder = new google.maps.Geocoder();

     geocoder.geocode( { 'address': address }, function(results, status) {
       if (status == google.maps.GeocoderStatus.OK) {
         map.setCenter(results[0].geometry.location);

        //  var infowindow = new google.maps.InfoWindow({
        //    content: contentString
        //  });

         var marker = new google.maps.Marker({
             map: map,
             Title: '' + address,
             position: results[0].geometry.location
         });

         marker.addListener('click', function() {
           infowindow.open(map, marker);


         })

       } else {
        //  alert("Geocode was not successful for the following reason: " + status);
       }
     });
   }

  // $(for (var i = 0; i < restaurantArray.length; i++){
  //    debugger;
  //    var address = restaurantArray[i].getAttribute('data-address');
  //    $(codeAddress(address));
  //  });
  //  $(codeAddress);

  $(initialize);


    var createMarkers = function() {
      debugger;
      for (var i = 0; i < restaurantArray.length; i++){
         debugger;
         var address = restaurantArray[i].getAttribute('data-address');
         $(codeAddress(address));
      }
    };

 })();
