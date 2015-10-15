var Map = {
      var __markers : [],
      var __restaurants : [],
      var __geocoder,
      var __map,


      initialize : function() {
        var portland = {lat: 45.5204527, lng: -122.673812};

        map = new google.maps.Map(document.getElementById("map"), {
          zoom: 15,
          center: portland,
          mapTypeId: google.maps.MapTypeId.ROADMAP
      },

      setMapOnRestaurantMarkers : function(map) {
        restaurants = document.getElementsByClassName("restaurant-address");
        for (var i=0; i< markers.length; i++) {
          markers[i].setMap(map);
        }
      },

      clearMarkers : function() {
        setMapOnRestaurantMarkers(null);
      },

      showMarkers : function() {
        setMapOnRestaurantMarkers(map);
      },

      deleteMarkers : function() {
        clearMarkers();
        markers = [];
      },

      getSelectedCategoryMarkers: function() {
        for (var i = 0; i < restaurantArray.length; i++){
           debugger;
           var address = restaurantArray[i].getAttribute('data-address');
           $(codeAddress(address));
        }
      },

      codeAddress : function(address) {
        // debugger;
        geocoder = new google.maps.Geocoder();

         geocoder.geocode( { 'address': address }, function(results, status) {
           if (status == google.maps.GeocoderStatus.OK) {
             map.setCenter(results[0].geometry.location);

             var marker = new google.maps.Marker({
                 map: map,
                 Title: '' + address,
                 position: results[0].geometry.location
             });

             markers.push(marker);

             marker.addListener('click', function() {
               infowindow.open(map, marker);
             })

           } else {
            // alert("Geocode was not successful for the following reason: " + status);
           }
         });
       }

 })();
