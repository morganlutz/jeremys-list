var Map = {
       __markers : [],
       __restaurants : [],
       __addresses : [],
       __map : null,


      initialize : function() {
        var portland = {lat: 45.5204527, lng: -122.673812};

        Map.__map = new google.maps.Map(document.getElementById("map"), {
          zoom: 10,
          center: portland,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        })
      },

      createMarkers : function () {
        // debugger;
        Map.__addresses = Map.getSelectedCategoryAddresses();
        for (var i = 0; i < Map.__addresses.length; i++) {
          $.getJSON('http://maps.googleapis.com/maps/api/geocode/json?address=' + Map.__addresses[i] + '&sensor=false', null, function (data) {
            // debugger;
            var markerResults = data.results[0].geometry.location
            var latlng = new google.maps.LatLng(markerResults.lat, markerResults.lng);
            var marker = new google.maps.Marker({
                          position: latlng,
                          map: Map.__map
                        });
            Map.__markers.push(marker);
          });
        }
        return Map.__markers;
        console.log(Map.__markers);
      },

      setMapOnRestaurantMarkers : function(map) {
        Map.initialize();
        for (var i=0; i< Map.__markers.length; i++) {
          Map.__markers[i].setMap(map);
        }
      },

      clearMarkers : function() {
        Map.setMapOnRestaurantMarkers(null);
      },
      //
      // showMarkers : function() {
      //   Map.setMapOnRestaurantMarkers(Map.__map);
      // },
      //
      deleteMarkers : function() {
        Map.clearMarkers();
        Map.__markers = [];
      },

      getSelectedCategoryAddresses: function() {
        var addresses = [];
        Map.__restaurants = document.getElementsByClassName('restaurant-address');
        for (var i = 0; i < Map.__restaurants.length; i++){
           addresses.push(Map.__restaurants[i].getAttribute('data-address'));
        }
        return addresses;
      },
      //
      // codeAddress : function(address) {
      //   var geocoder = new google.maps.Geocoder();
      //   var splitAddress = address.split(' ').join('+');
      //    geocoder.geocode( { 'address': address }, function(results, status) {
      //      if (status == google.maps.GeocoderStatus.OK) {
      //       //  Map.__map.setCenter(results[0].geometry.location);
      //        var contentString = '<strong>' + name + '</strong>' + '<br>'
      //        + '<a href=\'' + 'https://www.google.com/maps/place/'
      //        + splitAddress + '\'>' + 'Get directions!' + '</a>';
      //
      //        var marker = new google.maps.Marker({
      //            map: Map.__map,
      //            Title: '' + address,
      //            position: results[0].geometry.location
      //        });
      //
      //        var infowindow = new google.maps.InfoWindow({
      //          content: contentString
      //        });
      //
      //        marker.addListener('click', function() {
      //          infowindow.open(Map.__map, marker);
      //        });
      //
      //        Map.__markers.push(marker);
      //
      //        console.log(Map.__markers);
      //
      //      } else {
      //       // alert("Geocode was not successful for the following reason: " + status);
      //      }
      //    })
      //    return Map.__markers;
      //  }


 };
