import requests
import re
import codecs
import os
import time
requests.adapters.DEFAULT_RETRIES = 5
s=requests.session()
 
def load_page(url):
	response=requests.get(url)
	
	response.encoding="utf-8"

	data=response.content
	return data
def load_page_str(url):
	response=requests.get(url)
	response.encoding="utf-8"
	data=response.content.decode("utf-8")
	return data

def get_image(html,path):
	 
	regx=r'img.*?src="(.*?)".*?alt="(.*?)"'
	
	pattern=re.compile(regx)
	get_images=re.findall(pattern,repr(html))
	
	if  os.path.exists(path)==False:
		os.makedirs(path)
	for img in get_images:
		 
		 
		 

		print(img[0],img[1])
		 
		imgload=load_page(img[0])

		with open(path+'/car_'+img[1]+'.jpg','wb') as fb:
			fb.write(imgload)
			print('downloading %s pic'%img[1])
		 
	print('download done')
def get_image_new(html,path):
	 
	regx=r'img.*?src="(.{10,100}?_.{10,100}?jpg)".*?alt="(.*?)"'
	
	pattern=re.compile(regx)
	get_images=re.findall(pattern,repr(html))
	
	if  os.path.exists(path)==False:
		os.makedirs(path)
	for img in get_images:
		 
		 
		 

		print(img[0],img[1])
		strlist=img[0].split("_")
		strlist[len(strlist)-1]="1"+strlist[len(strlist)-1]
		print(strlist[len(strlist)-1])
		imgload=load_page("_".join(strlist))

		with open(path+'/car_'+img[1]+'.jpg','wb') as fb:
			fb.write(imgload)
			print('downloading %s pic'%img[1])
		 
	print('download done')
def get_imagecata(html):
	 
	regforname=r'<h2>(.*?)</h2>'
	patternforname=re.compile(regforname)
	titles=re.findall(patternforname,html)
	print(titles[0])
	rootpath='./car/'+titles[0]
	if os.path.exists(rootpath)==False:
		os.makedirs(rootpath)
	

	regforcata=r'href="(/photo/.{1,40}/)">.{3,100}<img.*?src="(.*?)".*?alt="(.*?)">.*?</a>'
	patternforcata=re.compile(regforcata)
	get_cata=re.findall(patternforcata,repr(html))
	
	
	for cata in get_cata:
		print('---------begin')
		print(cata[0]+'\n'+cata[1]+'\n'+cata[2]+'\n')
		print('---------end')
		subrootpath=rootpath+'/'+cata[2]

		if os.path.exists(subrootpath)==False:
			os.makedirs(subrootpath)
	
	
		suburl=baseurl+cata[0]
		
		 
		 
		
		subhtml=load_page_str(suburl)
		get_image_new(subhtml,subrootpath)
		time.sleep(1)
	
	 
		 

		 
 

	print('cata done')
baseurl='http://photo.bitauto.com'
url='http://photo.bitauto.com/master/13/#'
html=load_page_str(url)
get_imagecata(html)