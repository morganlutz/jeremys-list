var Map = {
       __markers : [],
       __restaurants : [],
       __map : null,


      initialize : function() {
        var portland = {lat: 45.5204527, lng: -122.673812};

        Map.__map = new google.maps.Map(document.getElementById("map"), {
          zoom: 15,
          center: portland,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        })
      },

      setMapOnRestaurantMarkers : function(map) {
        debugger;
        for (var i=0; i< Map.__markers.length; i++) {
          Map.__markers[i].setMap(map);
        }
      },

      clearMarkers : function() {
        debugger;
        Map.setMapOnRestaurantMarkers(null);
      },

      showMarkers : function() {
        Map.setMapOnRestaurantMarkers(Map.__map);
      },

      deleteMarkers : function() {
        debugger;
        Map.clearMarkers();
        Map.__markers = [];
      },

      getSelectedCategoryMarkers: function() {
        restaurants = document.getElementsByClassName('restaurant-address');
        // clearMarkers();
        for (var i = 0; i < restaurants.length; i++){
           var address = restaurants[i].getAttribute('data-address');
           Map.codeAddress(address);
        }
      },

      codeAddress : function(address) {
        var geocoder = new google.maps.Geocoder();
        var splitAddress = address.split(' ').join('+');
         geocoder.geocode( { 'address': address }, function(results, status) {
           if (status == google.maps.GeocoderStatus.OK) {
            //  Map.__map.setCenter(results[0].geometry.location);
             var contentString = '<strong>' + name + '</strong>' + '<br>'
             + '<a href=\'' + 'https://www.google.com/maps/place/'
             + splitAddress + '\'>' + 'Get directions!' + '</a>';

             var marker = new google.maps.Marker({
                 map: Map.__map,
                 Title: '' + address,
                 position: results[0].geometry.location
             });

             var infowindow = new google.maps.InfoWindow({
               content: contentString
             });

             marker.addListener('click', function() {
               infowindow.open(Map.__map, marker);
             });

             Map.__markers.push(marker);

             console.log(Map.__markers);

           } else {
            // alert("Geocode was not successful for the following reason: " + status);
           }
         })
         return Map.__markers;
       }


 };
