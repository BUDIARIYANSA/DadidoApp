# DadidoApp
Dadido App is an android application. Its task is only buy and sell NFT inside them. Can Register new account, create your own collection (One account only had one Collection). This app can also, adding funds (even the funds still localy). Find other people Item using search. Log Out and more.

Our Team on this Project :
- [Daniel Christian](https://github.com/dece441) (672019031)
- [Leonardo Ade Pradana Putra](https://github.com/LeonardoAde) (672019142)
- [Budi Ariyansa](https://github.com/BUDIARIYANSA) (672019076)

# Web Service
Our Web Service are on different repositories, this repositories only for our project app
>webservice-dadido on https://github.com/dece441/webservice-dadido.git

# Specification
Here are lists of material and file that being use in the project.
| Material                                         | Status Included        | File |
| :--                                            |     :---:              |     :-- |
| Theme NFT Marketplace                            | ***[dadido app]***     |   `-`  |
| UI                                               | ***[as same as posible to our first design]***       |   `-`    |
| Nested Fragment                                  | ***[included]***       | `File : HomeActivity.java`      |
| Adapter and Recyclerview                         | ***[included]***       | `File : Almost in all files`      |
| Intent                                           | ***[included]***       | `File : All Activity using intent`      |
| Activity                                         | ***[included]***       | `File : Every files`      |
| Fragment                                         | ***[included]***       | `File : Every View using Fragment, and custom Style`      |
| onActivityResult                                 | ***[included]***       | `File : ProfilActivity.java`      |
| Media                                            | ***[included]***       | `File : ProfilActivity.java, CreateCollectionActivity.java and CreateItemActivity.java`      |
| Permission : internet access and file access     | ***[included]***       | `File : AndroidManifest.xml`      |
| Googlemaps API                                   | ***[included]***       | `File : ProfilActivity.java`      |
| Webservice                                       | ***[included]***       | `File : All files using retrofit and in Webservice`      |
| Database with SQL/MySQL                          | ***[included]***       | `File : in Webservice`      |

# Project Installation
1. Open git-bash
2. Then, go to your android project folder or the default are `C:\Users\<your account>\AndroidStudioProjects`
3. And use `git clone` in your git-bash
4. And you have our projects app

# Troubleshooting
## Using Local Network
Using this method if webservices already running and setup correctly. And Also want to test, make changes and checking the web services
1. #### Using Android Virtual Device
   1. Make sure server Apache is running and Database is running, also SQL are updated
   2. Open `global_var.java`
   3. Edit the `webURL` change the ip or domain to `10.0.2.2`, So it'll become : `http://10.0.2.2/webservice-dadido`
1. #### Using Phone
   1. Make sure server Apache is running and Database is running, also SQL are updated
   2. Open `global_var.java`
   3. Edit the `webURL` change the ip or domain to your laptop/pc ip address, for example : `192.168.1.12`. So it'll become : `http://192.168.1.12/webservice-dadido`
## Using our Hosting Service
Using this method for easy to use.
1. Open `global_var.java`
2. Edit the `webURL` change to `https://www.kecapy.com/webservice-dadido`
