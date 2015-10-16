var Map = {
       __markers : [],
       __restaurants : [],
       __map : null,


      initialize : function() {
        var portland = {lat: 45.5204527, lng: -122.673812};

        map = new google.maps.Map(document.getElementById("map"), {
          zoom: 15,
          center: portland,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        })
      },

      setMapOnRestaurantMarkers : function(map) {
        for (var i=0; i< Map.__markers.length; i++) {
          Map.__markers[i].setMap(map);
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
        Map.__markers = [];
      },

      getSelectedCategoryMarkers: function() {
        debugger;
        restaurants = document.getElementsByClassName("restaurant-address");
        // clearMarkers();
        for (var i = 0; i < restaurants.length; i++){
           var address = restaurants[i].getAttribute('data-address');
           $(Map.codeAddress(address));
        }
      },

      codeAddress : function(address) {
        debugger;
        var geocoder = new google.maps.Geocoder();

         geocoder.geocode( { 'address': address }, function(results, status) {
           if (status == google.maps.GeocoderStatus.OK) {
             map.setCenter(results[0].geometry.location);

             var marker = new google.maps.Marker({
                 map: map,
                 Title: '' + address,
                 position: results[0].geometry.location
             });

             Map.__markers.push(marker);

             console.log(Map.__markers);

             marker.addListener('click', function() {
               infowindow.open(map, marker);
             })

           } else {
            // alert("Geocode was not successful for the following reason: " + status);
           }
         });
       }

 };
