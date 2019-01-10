<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
 
		        <div class="row">
		        	<p class="teal-text">Orario di fine:</p>
		        	<div class="input-field col s3">
		        		<form:select path="oraFine" class="icons" id="oraFine${registro.id}">
		        		
		        			<form:option value="1">01</form:option>
		        			<form:option value="2">02</form:option>
		        			<form:option value="3">03</form:option>
		        			<form:option value="4">04</form:option>
		        			<form:option value="5">05</form:option>
		        			<form:option value="6">06</form:option>
		        			<form:option value="7">07</form:option>
		        			<form:option value="8">08</form:option>
		        			<form:option value="9">09</form:option>
		        			<form:option value="10">10</form:option>
		        			<form:option value="11">11</form:option>
		        			<form:option value="12">12</form:option>
		        			<form:option value="13">13</form:option>
		        			<form:option value="14">14</form:option>
		        			<form:option value="15">15</form:option>
		        			<form:option value="16">16</form:option>
		        			<form:option value="17">17</form:option>
		        			<form:option value="18">18</form:option>
		        			<form:option value="19">19</form:option>
		        			<form:option value="20">20</form:option>
		        			<form:option value="21">21</form:option>
		        			<form:option value="22">22</form:option>
		        			<form:option value="23">23</form:option>
		        			<form:option value="0">00</form:option>
		        		
		        		</form:select>

		        	</div>
		        	
		        	<div class="input-field col s3">
		        		<form:select path="minutoFine" class="icons" id="minutoFine${registro.id}">
		        		
		        			<form:option value="0">00</form:option>
		        			<form:option value="1">01</form:option>
		        			<form:option value="2">02</form:option>
		        			<form:option value="3">03</form:option>
		        			<form:option value="4">04</form:option>
		        			<form:option value="5">05</form:option>
		        			<form:option value="6">06</form:option>
		        			<form:option value="7">07</form:option>
		        			<form:option value="8">08</form:option>
		        			<form:option value="9">09</form:option>
		        			<form:option value="10">10</form:option>
		        			<form:option value="11">11</form:option>
		        			<form:option value="12">12</form:option>
		        			<form:option value="13">13</form:option>
		        			<form:option value="14">14</form:option>
		        			<form:option value="15">15</form:option>
		        			<form:option value="16">16</form:option>
		        			<form:option value="17">17</form:option>
		        			<form:option value="18">18</form:option>
		        			<form:option value="19">19</form:option>
		        			<form:option value="20">20</form:option>
		        			<form:option value="21">21</form:option>
		        			<form:option value="22">22</form:option>
		        			<form:option value="23">23</form:option>
		        			<form:option value="24">24</form:option>
		        			<form:option value="25">25</form:option>
		        			<form:option value="26">26</form:option>
		        			<form:option value="27">27</form:option>
		        			<form:option value="28">28</form:option>
		        			<form:option value="29">29</form:option>
		        			<form:option value="30">30</form:option>
		        			<form:option value="31">31</form:option>
		        			<form:option value="32">32</form:option>
		        			<form:option value="33">33</form:option>
		        			<form:option value="34">34</form:option>
		        			<form:option value="35">35</form:option>
		        			<form:option value="36">36</form:option>
		        			<form:option value="37">37</form:option>
		        			<form:option value="38">38</form:option>
		        			<form:option value="39">39</form:option>
		        			<form:option value="40">40</form:option>
		        			<form:option value="41">41</form:option>
		        			<form:option value="42">42</form:option>
		        			<form:option value="43">43</form:option>
		        			<form:option value="44">44</form:option>
		        			<form:option value="45">45</form:option>
		        			<form:option value="46">46</form:option>
		        			<form:option value="47">47</form:option>
		        			<form:option value="48">48</form:option>
		        			<form:option value="49">49</form:option>
		        			<form:option value="50">50</form:option>
		        			<form:option value="51">51</form:option>
		        			<form:option value="52">52</form:option>
		        			<form:option value="53">53</form:option>
		        			<form:option value="54">54</form:option>
		        			<form:option value="55">55</form:option>
		        			<form:option value="56">56</form:option>
		        			<form:option value="57">57</form:option>
		        			<form:option value="58">58</form:option>
		        			<form:option value="59">59</form:option>
		        		
		        		</form:select>
		        	</div>
		        
		        </div>
		        	<div class="row">
		        		<div class="input-field col s12">
		            		<form:errors path="oraFine" cssClass="helper-text red-text chip" />
		            	</div>
		            </div>  
</html>