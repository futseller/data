require 'open-uri'

total_nations = 219
src_base_url = "http://fifa15.content.easports.com/fifa/fltOnlineAssets/8D941B48-51BB-4B87-960A-06A61A62EBC0/2015/fut/items/images"
dst_base_url = "../images/nations"

1.upto(total_nations) do |id|
  large_image_path = "#{dst_base_url}/large/nation_#{id}.png"
  normal_image_path = "#{dst_base_url}/normal/nation_#{id}.png"

  unless File.exist?(large_image_path)
    puts "Downloading nation #{id} large..."
    `curl -s #{src_base_url}/cardflagslarge/web/#{id}.png > #{large_image_path}`
  end
  unless File.exist?(normal_image_path)
    puts "Downloading nation #{id} normal..."
    `curl -s #{src_base_url}/cardflagssmall/web/#{id}.png > #{normal_image_path}`
  end
end